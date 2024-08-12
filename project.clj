(defproject todo-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.4"]
                 [ring "1.12.2"]
                 [compojure "1.7.1"]
                 [environ "1.2.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.7.3"]]
  :plugins [[lein-environ "1.0.1"]
            [lein-ancient "0.7.0"]]
  :profiles
  {:dev {:dependencies [[prone "2021-04-23"]]
         :env {:dev true}}}
  :repl-options {:init-ns todo-clj.core})
