(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

; True, False, and Conditionals
(defn can-vote
  [age]
  (if (>= age 18)
    (println "You can vote")
    (println "You can't vote")))

(defn can-do-more
  [age]
  (if (>= age 18)
    (do (println "You can drive")
        (println "You can vote"))))

(defn when-ex
  [tof]
  (when tof
    (println "1st thing")
    (println "2nd thing")))

(defn what-grade
  [age]
  (cond
    (< age 5) (println "Preschool")
    (= age 5) (println "Kindergarden")
    (and 
      (> age 5) 
      (<= age 18)) (format "Go to grade %d" (- age 5))
    :else "Go to college"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (can-vote 16)
  (can-do-more 25)
  (when-ex true)
  (what-grade 15))
