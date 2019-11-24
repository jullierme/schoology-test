import React from 'react';
import './AutoComplete.scss';
import { AutoCompleteService } from './AutoCompleteService';

import AsyncSelect from 'react-select/async';

interface AutocompleteProps {
    service: AutoCompleteService;
    value?: any;
    onChange?: any;
    style?: any;
}

const Autocomplete: React.FC<AutocompleteProps> = (
    props: AutocompleteProps
) => {
    const onChange = (item: any, actionMeta: any) => {
        if (
            actionMeta.action !== 'input-blur' &&
            actionMeta.action !== 'menu-close'
        ) {
            props.onChange(item);
        }
    };

    const loadOptions = (inputValue: string, callback: (data: any) => void) => {
        props.service.find(inputValue).then(callback);
    };

    return (
        <AsyncSelect
            id="asyncSelect"
            cacheOptions={false}
            defaultOptions={false}
            loadOptions={loadOptions}
            onChange={onChange}
        />
    );
};

export default Autocomplete;
