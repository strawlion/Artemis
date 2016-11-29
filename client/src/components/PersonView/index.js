import React from 'react';
import { connect } from 'react-redux'
import PersonTable from './PersonTable';
import PersonFilter from './PersonFilter';

import * as formatUtils from '../../utils/formatUtils';

export default connect(mapStateToProps)(PersonView);

function PersonView({ persons, onTextChanged }) {

    return (
        <div style={{ width: '75%' }}>
            <PersonFilter />
            <PersonTable persons={ persons } />
        </div>
  );
}


function mapStateToProps(state) {
  return {
      persons: formatUtils.getMockPersons(),
  };
}