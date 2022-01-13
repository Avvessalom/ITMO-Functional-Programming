(ns itmo-functional-programming.lab3.main)
(require '[clojure.string :as str])

(defn read-line-and-return []
  (let [in (str/trim (read-line))]
    (println in)))

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
                (read-line-and-return)
                (recur))

              (= in "2")
              (do
                (println "Enter points for prediction like \"x,y\": ")
                (read-line-and-return)
                (recur))

              (= in "3")
              (do
                (println "Prediction sobstvenno")
                (recur))

              (= in "4")
              (do
                (println "Pozsrit' to4e4ki")
                (recur))

              :else
              (first (filter #(= in (:id %)) options)))))))

(defn -main []
  (menu {:prompt  "Choose command"
         :options ["Add point" "Add point for prediction" "Predict" "Watch points" {:id "h" :text "Help"}]}))
