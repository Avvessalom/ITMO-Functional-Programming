(ns itmo-functional-programming.lab3.main
  (:require [itmo-functional-programming.lab3.lagrange :refer :all]
            [clojure.string :as str]))

(def function-points (ref []))
(def user-points (ref []))

(defn add-function-point [line]
  (let [split (vec (.split line ","))
        [x_s y_s & _] split
        x (Double/parseDouble x_s)
        y (Double/parseDouble y_s)]
    (println "Adding new function point: X:" x ", Y:" y)
    (-> function-points
        (alter conj [x y])
        (dosync))))

(defn add-user-point [line]
  (let [split (vec (.split line ","))
        [x_s & _] split
        x (Double/parseDouble x_s)]
    (println "Adding new user point: " x)
    (-> user-points
        (alter conj x)
        (dosync))))

(defn read-line-and-return []
  (let [in (str/trim (read-line))]
    in))

(defn menu [{:keys [prompt options]}]
  (let [options (map (fn [o idx]
                       (if (string? o)
                         {:id (str (inc idx)) :text o}
                         o)) options (range))
        valid-options (set (map :id options))]
    (loop []
      (when prompt
        (println)
        (println prompt)
        (println))
      (doseq [{:keys [id text]} options]
        (println (str " [" id "]") text))
      (println)
      (println "or press <enter> to cancel")

      (let [in (str/trim (read-line))]
        (cond (= in "")
              :cancelled

              (not (valid-options in))
              (do
                (println (format "\n-- Invalid option '%s'!" in))
                (recur))

              (= in "1")
              (do
                (println "Enter points like \"x,y\": ")
                (add-function-point (read-line-and-return))
                (recur))

              (= in "2")
              (do
                (println "Enter points for prediction like \"x\": ")
                (add-user-point (read-line-and-return))
                (recur))

              (= in "3")
              (do
                (println "Prediction for" @user-points ": ")
                (print (lagrange-interpolator @function-points @user-points))
                (recur))

              (= in "4")
              (do
                (print "Function points: ")
                (println @function-points)
                (print "User points: ")
                (println @user-points)
                (recur))

              (= in "5")
              :cancelled
              )))))

(defn -main []
  (menu {:prompt  "Choose command"
         :options ["Add point" "Add point for prediction" "Predict" "Watch points" "Exit"]}))
