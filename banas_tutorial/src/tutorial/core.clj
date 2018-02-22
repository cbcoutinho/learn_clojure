(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

; File I/O
(use `clojure.java.io)

(defn write-to-file
  [file text]
  (with-open [wrtr (writer file)]
    (.write wrtr text)))

(defn read-from-file
  [file]
  (try
    (println (slurp file))
    (catch Exception e
      (println "Error : " (.getMessage e)))))

(defn append-to-file
  [file text]
  (with-open [wrtr (writer file :append true)]
    (.write wrtr text)))

(defn read-line-from-file
  [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (println line))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (write-to-file "test.txt" "This is a sentence\nThis is a second line\n")
  (read-from-file "test.txt")
  (append-to-file "test.txt" "This is another line\n")                  
  (read-line-from-file "test.txt"))
