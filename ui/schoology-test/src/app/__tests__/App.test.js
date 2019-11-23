import React from 'react'
import ReactDOM from 'react-dom'
import App from '../App'

import { shallow } from 'enzyme'

it('renders without crashing', () => {
  const div = document.createElement('div')
  ReactDOM.render(<App />, div)
  ReactDOM.unmountComponentAtNode(div)
})

it('renders without crashing', () => {
  shallow(<App />)
})

it('renders welcome message', () => {
  const wrapper = shallow(<App />)
  const welcome = <h2>Schoology test - Autocomplete</h2>
  // expect(wrapper.contains(welcome)).toBe(true);
  expect(wrapper.contains(welcome)).toEqual(true)
})