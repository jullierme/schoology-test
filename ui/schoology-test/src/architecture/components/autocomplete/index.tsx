import React from 'react';
import './style.scss';
import { AutoCompleteService } from '../../service/AutoCompleteService';

interface AutocompleteProps {
  label: string;
  service: AutoCompleteService;
  value: any;
  onChange?: any;
}

const Autocomplete: React.FC<AutocompleteProps> = (
  props: AutocompleteProps
) => {
  const onChangeAutoComplete = (event: any) => {
    props.onChange(event);
  };

  return (
    <>
      <label>{props.label}</label>
      <input
        type="text"
        value={props.value}
        onChange={onChangeAutoComplete}
      />
    </>
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
