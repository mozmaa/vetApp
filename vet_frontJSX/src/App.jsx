import { createBrowserRouter ,RouterProvider } from 'react-router-dom'
import Login from './pages/Login/Login'
import Home from './pages/Home/Home'
import 'bootstrap/dist/css/bootstrap.min.css';
import RootLayout from './pages/RootLayout';



const router = createBrowserRouter([
    {
        path:'/',
        element: <RootLayout/>,
        children: [
            {path:'/' , element: <Home/>},
            {path: '/login', element: <Login/>}
        ]
    }
])

export default function App () {
    return (<><RouterProvider router={router}/>
        </>
    )
}