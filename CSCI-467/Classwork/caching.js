function cashify(fn) {
  var cache = {};
  return function() {
    var key = JSON.stringify(arguments);
    if (cache[key]) {
      return cache[key];
    }
    else {
      val = fn.apply(this, arguments);
      cache[key] = val;
      return val;
    }
  };
}

factorial = (n) => {
    if (n === 0) {
        return 1;
    }
    else {
        return n * factorial(n - 1);
    }
}

const cashedFactorial = cashify(factorial);
console.log(cashedFactorial(5));