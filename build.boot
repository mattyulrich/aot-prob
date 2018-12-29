(set-env! :dependencies '[[seancorfield/boot-tools-deps "0.4.5"]
                          [com.google.protobuf/protobuf-java "3.6.0"]
                          [boot-protobuf "0.3.0" :scope "test"]]
          :protobuf-version "3.6.0")

(require
 '[boot-tools-deps.core :refer [deps]]
 '[boot-protobuf.core :refer [compile-protobuf-java]])

(deftask build-proto []
  (comp
   (compile-protobuf-java)
   (javac :options ["-Xlint:none"])
   (sift :include #{#".*/.*.java$"} :invert true)))

(deftask startup []
  (comp
   (deps :quick-merge true)
   (build-proto)
   (aot :namespace #{'protobuf.impl.flatland.core})))
