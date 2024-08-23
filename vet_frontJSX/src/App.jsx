import { createBrowserRouter ,RouterProvider } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

import SignIn from './pages/signUpIn/SignIn.jsx'
import RootLayout from './pages/RootLayout';
import HomePage from './pages/home/HomePage.jsx';
import SignUp from './pages/signUpIn/SignUp.jsx';
import ErrorPage from './pages/error/ErrorPage.jsx';
import Ambulances from './pages/ambulance/Ambulances.jsx';



const router = createBrowserRouter([
    {
        path:'/',
        element: <RootLayout/>,
        errorElement: <ErrorPage/>,
        children: [
            {path:'/' , element: <HomePage/>},
            {path:'/ambulances' , element: <Ambulances/>},
            {path: '/signIn', element: <SignIn/>},
            {path: '/signUp', element: <SignUp/>}
        ]
    }
])

export default function App () {
    return (<><RouterProvider router={router}/>
        </>
    )
}