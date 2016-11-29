import React from 'react';
import { connect } from 'react-redux'
import { Card } from 'react-materialize';

import actions from '../../state/actions';

export default connect(mapStateToProps, mapDispatchToProps)(PersonFilter)

function PersonFilter({ searchText, onTextChanged }) {

    return (
        <Card>
            Stugg
        </Card>
  );
}

function mapStateToProps(state) {
  return {
      searchText: state.searchText,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    onTextChanged,
  };

  function onTextChanged(text) {
      dispatch(actions.searchTextChanged(text));
  }
}
