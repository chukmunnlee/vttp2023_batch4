// scalar
const name = 'fred'
// reference
const employee = {
   name: 'fred',
   email: 'fred@@gmail.com'
}

const hello = (name) => {
   return `hello ${name}`
}

console.info('name ', name)
console.info('employee ', employee)

// print the hello value
console.info('hello: ', hello)
// invoke hello
console.info('say hello: ', hello(employee.name))