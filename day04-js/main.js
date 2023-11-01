var name = "fred" // string
var age = 50 // number
var married = true // boolean
var nums = [ 1, 2, 3, 4, 5 ]

var printIt = function(name, age, status) {
   console.info(`Name: ${name}, Age: ${age}, Married: ${married}`)
}

var abc = printIt

function hello(name) {
   console.info('Hello ', name)
}

// Invoke the function 
printIt(name, age, married)
abc(name, age, married)
// Cannot invoke because it is not a function
//name()

console.info(">>> printIt", printIt)
console.info(">>> name", name)
console.info(">>> nums[0]", nums[0])