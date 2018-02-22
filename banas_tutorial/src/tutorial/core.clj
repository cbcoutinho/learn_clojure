(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

; Agents
(defn agent-ex
  []

  (def tickets-sold (agent 0))
  (send tickets-sold + 15)
  (println)
  (println "Tickets " @tickets-sold))

  ;(send tickets-sold + 10)
  ;(await-for 100 tickets-sold)
  ;(println "Tickets " @tickets-sold)

  ;(shutdown-agents))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (agent-ex))

