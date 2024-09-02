import { Fragment } from "react"

import MainNavigation from "../../components/navigation/MainNavigation"

export default function ErrorPage () {
    return (
        <Fragment>
            <MainNavigation/>
            <main>
                <h1>Error</h1>
                <p>Page not found</p>
            </main>
        </Fragment>
    )
}