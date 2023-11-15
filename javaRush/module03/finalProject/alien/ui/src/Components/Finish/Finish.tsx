import React from 'react';
import {useTranslationTyped} from '@/Utils';
import {Start} from '@/Components/Start/Start';

export const Finish: React.FC = () => {

    const {t} = useTranslationTyped();

    return (<div>
        <Start/>
    </div>);
}
