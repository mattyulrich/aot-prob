(ns aot-prob.core
  (:require [protobuf.core :as protobuf])
  (:import Test$TestMessage))

(defn build-message [message]
  (let [ts (.getTime (java.util.Date.))
        msg {:timestamp ts :message message}]
    (protobuf/create Test$TestMessage msg)))
