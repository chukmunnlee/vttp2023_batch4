const nums = [1, 2, 3, 4, 5, 6]

const isEven = v => {
   return 0 == (v % 2)
}
const mul2 = v => {
   return v * 2
}

let total = 0
for (let i = 0; i < nums.length; i++) {
   let curr = nums[i]
   // filter
   if (!isEven(curr))
      continue
   // map
   curr = mul2(curr)
   // if no total => n numbers in array
   // total => 1
   // reduce
   total = total + curr
   console.info('total: ', total)
}

const processed = nums
   //.filter(function(v) { return 0 != (v % 2 )})
   .filter(v => 0 != (v % 2))
   .map(v => v * 2)
   .reduce((acc, v) => acc + v)

console.info('>>> processed: ', processed)

