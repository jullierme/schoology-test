import React from 'react';
import ReactDOM from 'react-dom';
import { shallow } from 'enzyme';

import Autocomplete from '../../../../architecture/components/autocomplete/AutoComplete';
import { AutoCompleteServiceImpl } from '../../../../architecture/components/autocomplete/AutoCompleteService';

import toJson from 'enzyme-to-json';

class TestAcService extends AutoCompleteServiceImpl {
    fieldName = 'name';
    fieldValue = 'abbreviation';
}

const service = new TestAcService();

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<Autocomplete service={service} />, div);

    ReactDOM.unmountComponentAtNode(div);
});

it('renders without crashing', () => {
    const wrapper = shallow(<Autocomplete service={service} />);

    expect(wrapper).toMatchSnapshot();

    console.log(toJson(wrapper));
});

