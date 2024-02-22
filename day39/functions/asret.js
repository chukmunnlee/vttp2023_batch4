const hello = (name) => {
   return `hello ${name}`
}

const apply = (func, value) => func(value)

const sayHello = (name) => {
   return () => {
      return hello(name)
   }
}

const helloFred = sayHello('fred')
const helloBarney = sayHello('barney')

console.info('helloFred: ', helloFred)
console.info('helloFred(): ', helloFred())
console.info('helloBarney(): ', helloBarney())