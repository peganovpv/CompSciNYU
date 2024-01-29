const nums = [2,4,6,8]
const odds = [3,5,7]

const logArray = (arr) => {
    for(let i = 0; i < arr.length; i++) {
        console.log(arr[i])
    }
}

const logArrayDouble = (arr) => {
    for(let i = 0; i < arr.length; i++) {
        console.log(arr[i] * 2)
    }
}

// Higher Order Function
// Seen this already as an array builder
const myForEach = (arr, cb) => {
    for(let i = 0; i < arr.length; i++) {
        cb(arr[i])
    }
}

const myMap = (arr, tr) => {
    newArr = []
    for(let i = 0; i < arr.length; i++) {
        newArr[i] = tr(arr[i])
    }
    return newArr
}

const result = myMap(arr, n => n * 2)

// Reduce is a higher order function that takes an array and a callback
// and returns a single value
// cb performs the reduction operation
const myReduce = (arr, cb, init) => {
    let acc = init
    for(let i = 0; i < arr.length; i++) {
        acc = cb(acc, arr[i])
    }
    return acc

}

const debuggedParseInt = debug(parseInt)
debuggedParseInt("10", 2)
// prints out 11 and 2 and return 3

const debug = (cb) => {
    return (str, base) => {
        const result = cb(str, base)
        console.log(result)
        console.log(base)
        return result
    }
}

console.log(result)
myForEach(nums, (num) => {
    console.log(num)
})
logArray(nums)
logArrayDouble(nums)