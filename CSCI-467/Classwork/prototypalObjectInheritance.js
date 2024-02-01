// Objects/Prototypal Inheritance
// this keyword
// when to use arrow functions

const person = {
    name: 'Peter'
}

console.log(person.name)

Object.hasOwnProperty('name')

// If you want the protoyype of an Object you must use Object.getPrototypeOf

const array = [1,4,6,2,9,3,5,7,8]
array.push(10)

console.log('push' in array)
console.log(Object.getPrototypeOf(array) === Array.prototype)

Object.hasOwnProperty(array.prototype, 'map')

const obj = {
    name: 'foo'
}

function f() {
    console.log(this.name)
}

f.call(obj)

class WereWolf {
    constructor(name) {
        this.name = name
    }

    howl(target) {
        console.log(this.name + ' howls ' + 'at ' + target)
    }
}

const werewolf = new WereWolf('Peter')
werewolf.howl('moon')

console.log(typeof werewolf)
console.log(typeof WereWolf)

const cat = {
    name: 'Fluffy',
    nationality: 'american'
}

function meow(){
    if(this.nationality === 'american'){
        console.log('meow')
    } else {
        console.log('miau')
    }
}

// two ways to call a function
cat.meow = meow()
meow.call(cat)

// arrow functions
const printer = {
    prefix: '=>',
    words: ['world', 'mars', 'jupiter'],
    print() {
        this.words.forEach(function(word) {
            console.log(this.prefix + ' ' + word)
        })
    } 
}

printer.print()

// in clientside JS, you shouldnt use 'this' keyword in an event handler
// use arrow functions to preserve the context of 'this'