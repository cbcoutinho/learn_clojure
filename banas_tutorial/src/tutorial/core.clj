(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(use 'clojure.java.io)

; Advanced functions
(take 2 [1 2 3])
(drop 1 [1 2 3])
(take-while neg? [-1 0 1])
(drop-while neg? [-1 0 1])
(filter #(> % 2) [1 2 3 4])


(defn -main
  "I don't do a whole lot ... yet."
  [& args])
