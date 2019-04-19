const { Component } = React;

const { Switch, Route, Link, HashRouter, Redirect } = ReactRouterDOM;

function App() {
  return (
    <HashRouter>
      <Switch>
        <Route exact path="/Login" component={Login} />
        <Route exact path="/Home" component={Home} />
        {JSON.parse(localStorage.getItem('User')).role == 'admin' ? <Route exact path="/AdminUser" component={AdminUser} /> : null}
        <Route exact path="**" render={() => (<Redirect to="Home" />)} />
      </Switch>
    </HashRouter>
  );
}
