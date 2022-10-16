import type { NextPage } from 'next'

const Home: NextPage = () => {

    let displayAlert = () => {
        alert('Clicked');
    }

    return (<>
                <h1 className="text-3xl font-bold underline">Donations SPA</h1>
                <button onClick={displayAlert}>Click</button>
            </>)
}

export default Home
