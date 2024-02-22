const power = (v, r) => {
   let total = 1
   for (let i = 0; i < r; i++)
      total *= v 
   return total
}

const mkPower = (radix) => {
   return (value) => {
      return power(value, radix)
   }
}

console.info('>>> power(2, 3) ', power(2, 3))
console.info('>>> power(3, 3) ', power(3, 3))

const square = mkPower(2)
const cube = mkPower(3)
console.info('> square(4): ', square(4))
console.info('> cube(4): ', cube(4))

const curry = (func, value) => {
   return function() {
      return func(value, ...arguments)
   }
}
