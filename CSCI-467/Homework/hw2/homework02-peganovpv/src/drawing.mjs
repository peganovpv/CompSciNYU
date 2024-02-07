// drawing.js

import { writeFile } from 'fs/promises';

class GenericElement {

    constructor(type) {
        this.type = type;
        this.attrs = {};
        this.children = [];
    }

    addAttr(key, value) {
        this.attrs[key] = value;
    }

    addAttrs(attrs) {
        Object.assign(this.attrs, attrs);
    }

    removeAttrs(keys) {
        keys.forEach(key => delete this.attrs[key]);
    }

    addChild(child) {
        this.children.push(child);
    }

    toString() {
        const attrs = Object.keys(this.attrs).map(key => `${key}="${this.attrs[key]}"`).join(' ');
        const children = this.children.map(child => child.toString()).join('\n');
        return `<${this.type} ${attrs}>${children}</${this.type}>`;
    }

    write(filename, callback) {
        writeFile(filename, this.toString(), callback);
    }
   
}

class RootElement extends GenericElement {

    constructor() {
        super('svg');
        this.addAttrs({xmlns: 'http://www.w3.org/2000/svg', version: '1.1'});
    }

}

class RectangleElement extends GenericElement {

    constructor(x, y, width, height, fill) {
        super('rect');
        this.addAttrs({x, y, width, height, fill});
    }
   
}

class TextElement extends GenericElement {

    constructor(x, y, fontSize, fill, content) {
      super('text'); 
      this.addAttrs({ x, y, 'font-size': fontSize, fill });
      this.content = content;
    }
  
    toString() {
      return `<text ${Object.keys(this.attrs).map(key => `${key}="${this.attrs[key]}"`).join(' ')}>${this.content}</text>`;
    }

}
  

// the following is used for testing
// create root element with fixed width and height
const root = new RootElement();
root.addAttrs({width: 800, height: 170, abc: 200, def: 400});
root.removeAttrs(['abc','def', 'non-existent-attribute']);

// create circle, manually adding attributes, then add to root element
const c = new GenericElement('circle');
c.addAttr('r', 75);
c.addAttr('fill', 'yellow');
c.addAttrs({'cx': 200, 'cy': 80});
root.addChild(c);

// create rectangle, add to root svg element
const r = new RectangleElement(0, 0, 200, 100, 'blue');
root.addChild(r);

// create text, add to root svg element
const t = new TextElement(50, 70, 70, 'red', 'wat is a prototype? 😬');
root.addChild(t);

// show string version, starting at root element
console.log(root.toString());

// write string version to file, starting at root element
root.write('test.svg', () => console.log('done writing!'));