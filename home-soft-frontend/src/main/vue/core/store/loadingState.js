export const LOADING_STATE = Object.freeze({
  UNUSED:       Symbol("unused"),
  LOADED:       Symbol("loaded"),
  TO_REFRESH:   Symbol("toRedresh"),
  DISCARDED:    Symbol("discarded"),
  LOADING:      Symbol("loading"),
  FAIL:         Symbol("fail")
});
