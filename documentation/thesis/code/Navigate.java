var backButton = new Button("Back to orders");
backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(RestaurantOrdersView.class));
