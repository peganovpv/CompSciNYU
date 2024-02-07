// hoffy.mjs

function getEvenParam(...args){

    return args.length === 0 ? [] : args.filter((_, index) => index % 2 === 0);
    
}

function myFlatten(arr2d){

    return arr2d.reduce((acc, curr) => acc.concat(curr), []);

}

function maybe(fn){

    return function(...args){
        if(args.some(arg => arg === null || arg === undefined)){
            return undefined;
        } else {
            return fn(...args);
        }
    };

}

function filterWith(fn){

    return function(array) {
        return array.filter(fn);
      };

}

function repeatCall(fn, n, arg) {

    if (n <= 0) {return;} 

    fn(arg); 
    repeatCall(fn, n - 1, arg);

}

function limitCallsDecorator(fn, n){

    let count = 0;

    return function(...args){
        if(count < n){
            count++;
            return fn(...args);
        } else {
            return undefined;
        }
    };

}

function myReadFile(filename, successFn, errorFn){

    const fs = require('fs');

    fs.readFile(filename, 'utf8', (err, data) => {
        if(err){
            errorFn(err);
        } else {
            successFn(data);
        }
    });

    return undefined; 

}

function rowsToObjects(data){

    const { headers, rows } = data;

    return rows.map(row => {
      const obj = {};
      headers.forEach((header, index) => {
        obj[header] = row[index];
      });

      return obj;

    });

}

export { getEvenParam, myFlatten, maybe, filterWith, repeatCall, limitCallsDecorator, myReadFile, rowsToObjects };