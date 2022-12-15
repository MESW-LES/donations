import React, { useState } from "react";

export const SessionContext = React.createContext({
  sessionUser: { user: {}, role: "" },
  setSessionUser: (sessionUser) => {},
});

export const SessionContextProvider = (props) => {
  const setSessionUser = (sessionUser) => {
    setState({ ...state, sessionUser: sessionUser });
  };

  const initState = {
    sessionUser: {},
    setSessionUser: setSessionUser,
  };

  const [state, setState] = useState(initState);

  return (
    <SessionContext.Provider value={state}>
      {props.children}
    </SessionContext.Provider>
  );
};
