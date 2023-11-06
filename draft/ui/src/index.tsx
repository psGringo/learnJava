import React from 'react';
import ReactDOM from 'react-dom';
import {App} from "@/Components/App/App";
import {Provider} from "react-redux";
import {appStore} from "@/Store/ConfigureStore";


ReactDOM.render(
    <Provider store={appStore}>
        <React.StrictMode>
            <App/>
        </React.StrictMode>
    </Provider>,

    document.getElementById('app')
);
