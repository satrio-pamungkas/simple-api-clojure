(ns simple-api-clojure.core
    (:require [toucan.db :as db]
              [toucan.models :as models]
              [dotenv :refer [env app-env]]
              [ring.adapter.jetty :refer [run-jetty]]
              [compojure.api.sweet :refer [routes]]
              [simple-api-clojure.routes.route :refer [user-routes]])
    (:gen-class))

(def db-spec
    {:dbtype "postgres"
     :dbname (env :DB_NAME)
     :host (env :DB_HOST)
     :user (env :DB_USER)
     :password (env :DB_PASSWORD)})

(def app (apply routes user-routes))

(defn -main
    [& args]
    (db/set-default-db-connection! db-spec)
    (models/set-root-namespace! 'simple-api-clojure.models)
    (run-jetty app {:port 3000}))
