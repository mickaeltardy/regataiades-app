package org.mtdev.regataiades.view;

public interface DataView {
	boolean hasView();

	Class<? extends BaseView> getView();

	Object getData();
}
