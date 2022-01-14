(ns itmo-functional-programming.lab3.lagrange)

(defn numerator-f [i x xs]
  (reduce-kv #(if (== i %2)
                %1
                (* %1 (- x %3)))
             1
             xs))

(defn xs-denom
  [x y]
  (mapv (fn [fx fy]
          (/ fy (reduce #(if (== fx %2)
                          %1
                          (* %1 (- fx %2))) 1 x)))
        x y))

(defn lagrange [points]
  (let [xs (mapv first points)
        ys (mapv second points)]
    (fn [x]
      (reduce +
              (map-indexed #(* (numerator-f %1 x xs) %2)
                           (xs-denom xs ys))))))

(defn lagrange-interpolator [func-point user-points]
  (mapv (lagrange func-point) user-points))
