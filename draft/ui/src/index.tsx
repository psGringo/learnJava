import {Provider} from 'react-redux';
import {App} from '@/Components/App/App';
import {appStore} from '@/Store/ConfigureStore';
import {createRoot} from 'react-dom/client';

const container = document.getElementById('app')!;
const root = createRoot(container);
root.render(
    <Provider store={appStore}>
        <App/>
    </Provider>,
);
