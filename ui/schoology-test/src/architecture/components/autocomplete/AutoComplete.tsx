import React from 'react';
import './style.scss';
import { AutoCompleteService } from './AutoCompleteService';

import AsyncSelect from 'react-select/async';

import { Entity } from '../../service/Entity';

interface AutocompleteProps {
    service: AutoCompleteService<Entity>;
    value: any;
    onChange?: any;
    style?: any;
}

const Autocomplete: React.FC<AutocompleteProps> = (
    props: AutocompleteProps
) => {
    const onChangeAutoComplete = (item: any, actionMeta: any) => {
        if (
            actionMeta.action !== 'input-blur' &&
            actionMeta.action !== 'menu-close'
        ) {
            props.onChange(item);
            console.log('onChangeAutoComplete');
        }
    };

    const loadOptions = (inputValue: string, callback: (data: any) => void) => {
        props.service.find(inputValue, callback);
    };

    return (
        <AsyncSelect
            id="asyncSelect"
            styles={props.style}
            cacheOptions={false}
            defaultOptions={false}
            loadOptions={loadOptions}
            onChange={onChangeAutoComplete}
        />
    );
};

export default Autocomplete;

/*
  /*function setModel(e: React.ChangeEvent<HTMLInputElement>) {
    const value = e.target.value;

    props.setService((prevState: any) => {
      const aux = { ...prevState };

      aux[props.name] = value;

      return aux;
    });
  }

Autocomplete.propTypes = {
  label: PropTypes.string.isRequired,
  model: PropTypes.object.isRequired,
  setModel: PropTypes.func.isRequired,
  name: PropTypes.string.isRequired,

  onChange: PropTypes.func,
  sizeList: PropTypes.number,
}*/

/*Autocomplete.defaultProps = {
  sizeList: config.get('autocomplete').defaultSizeList,
}*/
