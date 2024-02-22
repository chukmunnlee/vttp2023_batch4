// functional type
const hello = function(name) {
   return `Hello ${name}`
}
let h = hello

// <button type="button" (click)="process($event)">
/* prom.then(
 *    function(result) {
      }     
   )
   arr.filter, map, forEach
   arr.map(v => { })

   thrPool.submit(
      () -> {
         System.out.printf("hello, world\n");
      }
   )
 */
function apply(func, value) {
   return func(value)
}

const name = 'fred'
console.info('hello fred: ', hello(name))

console.info('apply(hello, name): ', apply(hello, name))
console.info('apply(hello, apply(hello, name)): ', apply(hello, apply(hello, name)))


/*
console.info('apply: ', apply)
console.info('hello: ', hello)
console.info('h: ', h)

console.info('hello fred: ', hello('fred'))
console.info('h barney: ', h('barney'))
*/