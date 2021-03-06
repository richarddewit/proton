(ns proton.layers.lang.elixir.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env :refer [set-grammar]])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies]]))

(defmethod init-layer! :lang/elixir
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/elixir)
  (register-layer-dependencies :tools/linter [:linter-elixirc]))

(defn- elixir-mode-init []
 (atom-env/set-grammar "Elixir"))

(defmethod get-initial-config :lang/elixir []
  [["autocomplete-elixir.erlangHome" "/usr/local/bin"]
   ["autocomplete-elixir.elixirPath" "/usr/local/bin"]])

(defmethod get-packages :lang/elixir []
  [:language-elixir
   :autocomplete-elixir
   :iex])

(defmethod describe-mode :lang/elixir []
  {:mode-name :elixir
   :atom-grammars ["Elixir"]
   :mode-keybindings
   {:i {:category "iex"
        :l {:action "iex:open-split-right"
            :title "create right"}
        :h {:action "iex:open-split-left"
            :title "create left"}
        :k {:action "iex:open-split-up"
            :title "create above"}
        :j {:action "iex:open-split-down"
            :title "create below"}
        :n {:action "iex:open"
            :title "new iex"}}}
   :init elixir-mode-init})

(defmethod get-keymaps :lang/elixir []
  [{:selector "body atom-workspace .iex .terminal" :keymap [["space" "native!"]]}])

(defmethod get-keybindings :lang/elixir [] {})
