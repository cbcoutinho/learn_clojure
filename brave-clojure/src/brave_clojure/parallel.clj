(ns brave-clojure.parallel)

;;; Ch 7: The Sacred Art of Concurrent and Parallel Programming

; This shows the difference between futures and immediate execution
(deref (future (Thread/sleep 4000)
               (println "I'll print after 4 sec")))
(println "I'll print this immediately")

; Bind the value of a future to a symbol, result gets cached
(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))


;; Notify users when their headshot gets uploaded:
(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])
(defn email-user [email-address]
  (println "Sending headshot notification to" email-address))
(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)
(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
            (force notify))))
