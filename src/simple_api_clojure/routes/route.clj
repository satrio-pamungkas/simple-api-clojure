(ns simple-api-clojure.routes.route
    (:require [compojure.api.sweet :refer [POST GET]]
              [simple-api-clojure.handlers.handler :as api]
              [simple-api-clojure.schemas.user :refer [UserRequestSchema]]
              [schema.core :as s]))


(def user-routes
    [(POST "/users" []
        :body-params [create-user-req UserRequestSchema]
        (-> (api/create-user-handler create-user-req)))
     (GET "/users/:id" []
        :path-params [id :- s/Int]
        (-> (api/get-user-handler id)))
     (GET "/users" []
        (-> (api/get-users-handler)))])