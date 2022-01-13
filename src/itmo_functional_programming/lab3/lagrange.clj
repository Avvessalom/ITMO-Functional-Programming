(ns itmo-functional-programming.lab3.lagrange)

(defn numerator-f [i x s]
  (reduce-kv #(if (== i %2)
                %1
                (* %1 (- x %3)))
             1
             s))

(defn xs-denom
  [x y xs]
  (mapv (fn [x y]
          (/ y (reduce #(if (== x %2)
                          %1
                          (* %1 (- x %2))) 1 xs)))
        x y))

(defn lagrange [points]
  (let [xs (mapv first points)
        ys (mapv second points)]
    (fn [x]
      (reduce +
              (map-indexed #(* (numerator-f %1 x xs) %2)
                           (xs-denom xs ys xs))))))
