(ns seesaw-test.core
  (:use seesaw.core))

  (defn -main
    "The main function"
    [& args]
    (defn handler
      [event]
      (alert event
        (str "<html>Hello from <b>Clojure</b>. Button "
        (.getActionCommand event) " clicked.")))

        (-> (frame :title "Hello Swing" :on-close :exit
        :content (button :text "Click Me" :listen [:action handler]))
        pack!
        show!))
