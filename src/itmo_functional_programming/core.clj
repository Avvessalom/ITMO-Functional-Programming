(ns itmo-functional-programming.core)

;By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
;
;What is the 10 001st prime number?

(defn sqrt-range [n]
  (take-while #(>= n (* % %)) (range 2 1.0E15)))

(defn is-prime? [n]
  (if (< n 2)
    false
    (let [s (sqrt-range n)]
      (every? true? (map #(not= 0 (rem n %)) s))
      )))

(defn primes [num]
  (time (last (take num (filter is-prime? (range)))))
  )

