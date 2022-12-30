import "../styles/globals.css";
import type { AppProps } from "next/app";
import Head from "next/head";

import "primereact/resources/themes/md-light-indigo/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "../styles/layout/layout.scss";
import "../styles/demo/Demos.scss";
import "primeflex/primeflex.css";
import { useState } from "react";
import { SessionContextProvider } from "../context/SessionContext";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <link rel="icon" href="/favicon.ico" />
        <title>Donations App</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        <meta name="theme-color" content="#000000" />
      </Head>
      <SessionContextProvider>
        <Component {...pageProps} />
      </SessionContextProvider>
    </>
  );
}

export default MyApp;
