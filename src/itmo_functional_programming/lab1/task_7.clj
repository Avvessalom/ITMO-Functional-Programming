(ns itmo-functional-programming.lab1.task-7
  "- By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
  - What is the 10 001st prime number?"
  )

(defn sqrt-range [n]
  (take-while #(>= n (* % %)) (range 2 1.0E15)))

(defn is-prime? [n]
  (if (< n 2)
    false
    (let [s (sqrt-range n)]
      (every? true? (map #(not= 0 (rem n %)) s)))))

(defn primes-to [n]
  (filter is-prime? (range 2 (inc n))))

(defn primes-seq [n]
  (take n (filter is-prime? (range))))

(defn n-th-prime-number-filtered [num]
  (last (take num (filter is-prime? (range)))))

(defn n-th-prime-number-filtered-with-macro [n]
  (->> (range)
       (filter is-prime?)
       (take n)
       (last)))

(defn n-th-prime-number-loop [n]
  (loop [iter 1
         primes []]
    (if (< (count primes) n)
      (if (is-prime? iter)
        (recur (inc iter) (conj primes iter))
        (recur (inc iter) primes))
      (last primes))))

(defn task-7-report []
  (do
    (println (format "Task 7 solutions:
                * filtered: %3d
                * loop:     %3d" (n-th-prime-number-filtered 10001) (n-th-prime-number-loop 10001)))
    (println "Results are equal:"
             (apply = [
                       (time (n-th-prime-number-loop 10001))
                       (time (n-th-prime-number-filtered 10001))
                       ]))))
