const add = (x, y) => {
   return x + y
}
const power = (x, y) => {
   return Math.pow(x, y)
}

const apply = (fn, x, y) => {
   return fn(x, y)
}

console.info('3 + 4 = ', add(3, 4))
console.info('3 + 4 = ', apply(add, 3, 4))
console.info('3^4 = ', apply(power, 3, 4))