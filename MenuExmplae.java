package com.magna.xmsystem.xmadmin.ui.parts.objexpcontextmenu;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;

import com.magna.xmsystem.xmadmin.message.Message;
import com.magna.xmsystem.xmadmin.message.MessageRegistry;
import com.magna.xmsystem.xmadmin.ui.parts.objexp.ObjectExplorer;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.IAdminTreeChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectAppFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectAppNotFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectAppProtected;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaProjectStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaUserAppFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaUserAppNotFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaUserAppProtected;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdminAreaUserApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.adminarea.AdministrationArea;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.baseapplicationmodel.BaseAppProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.baseapplicationmodel.BaseAppStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.baseapplicationmodel.BaseAppUserApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.baseapplicationmodel.BaseApplication;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.projectapplicationmodel.ProjectAppAdminAreaChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.projectapplicationmodel.ProjectAppAdminAreaProjects;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.projectapplicationmodel.ProjectAppUsers;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.projectapplicationmodel.ProjectApplication;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.startapplicationmodel.StartAppAdminAreas;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.startapplicationmodel.StartAppProjects;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.startapplicationmodel.StartAppUsers;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.startapplicationmodel.StartApplication;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.userapplicationmodel.UserAppAdminAreas;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.applicationmodel.userapplicationmodel.UserApplication;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProAARelAssignEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProAARelRemoveEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProjectActivateEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProjectCreateEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProjectDeactivateEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.ProjectDeleteEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.UserProRelExpEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.UserProjectRelAssignEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.notification.UserProjectRelRemoveEvtAction;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.configuration.roles.Role;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.directory.DirectoryApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.Project;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaProjectAppFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaProjectAppNotFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaProjectAppProtected;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectAdminAreaStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectUserAAProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectUserAdminAreaChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.projects.ProjectUserChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.refs.RelationObj;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.Site;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaProjectChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaProjectStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaStartApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaUserAppFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaUserAppNotFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaUserAppProtected;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminAreaUserApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminProjectAppFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminProjectAppNotFixed;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdminProjectAppProtected;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.sitemodel.SiteAdministrationChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.User;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserAAUserApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserAdminAreaChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserProjectAAProjectApplications;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserProjectAdminAreaChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserProjectChild;
import com.magna.xmsystem.xmadmin.ui.treeviewer.admintree.model.users.UserStartApplications;
import com.magna.xmsystem.xmadmin.util.CommonConstants;

/**
 * The Class ObjExpContextMenu.
 */
public class ObjExpContextMenu {

	/** The create as. */
	CreateAsAction createAs;

	/** The change. */
	ChangeAction change;

	/** The delete. */
	DeleteAction delete;

	/** The activate. */
	ActivateAction activate;

	/** The de activate. */
	DeActivateAction deActivate;

	/** The remove relation. */
	RemoveRelationAction removeRelation;

	/** The goto action. */
	GoToAction gotoAction;

	/** The expiry days action. */
	ExpiryDaysAction expiryDaysAction;
	
	/** The activate rel. */
	ActivateRelAction activateRel;
	
	/** The de activate rel. */
	DeActivateRelAction deActivateRel;

	/** The messages. */
	@Inject
	@Translation
	private Message messages;
	
	@Inject
	private MessageRegistry registry;

	/** The tree viewer. */
	ObjectExplorer treeViewer;

	/** The eclipse context. */
	@Inject
	private IEclipseContext eclipseContext;

	/**
	 * Instantiates a new obj exp context menu.
	 *
	 * @param viewer
	 *            the viewer
	 */
	@Inject
	public ObjExpContextMenu(final ObjectExplorer viewer) {
		super();
		this.treeViewer = viewer;

	}

	/**
	 * Creates the context menu.
	 */
	@PostConstruct
	public void createContextMenu() {
		makeActions();
		hookContextMenu();
	}

	/**
	 * Hook context menu.
	 */
	private void hookContextMenu() {
		final MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			/**
			 * executed with menu is about to show
			 */
			@Override
			public void menuAboutToShow(final IMenuManager manager) {
				ObjExpContextMenu.this.fillContextMenu(manager, treeViewer);
			}
		});
		final Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
	}

	/**
	 * Fill context menu.
	 *
	 * @param manager the manager
	 * @param viewer the viewer
	 */
	private void fillContextMenu(final IMenuManager manager, final TreeViewer viewer) {
		if (viewer.getSelection().isEmpty()) {
			return;
		}
		if (viewer.getSelection() instanceof IStructuredSelection) {
			final IStructuredSelection selections = (IStructuredSelection) viewer.getSelection();
			final Object selectionObj = selections.getFirstElement();
			if (selectionObj instanceof RelationObj) {
				final RelationObj relationObj = (RelationObj) selectionObj;
				if (relationObj.getContainerObj() instanceof BaseAppProjectApplications
						|| relationObj.getContainerObj() instanceof BaseAppUserApplications
						|| relationObj.getContainerObj() instanceof BaseAppStartApplications
						|| relationObj.getContainerObj() instanceof UserAppAdminAreas
						/*|| relationObj.getContainerObj() instanceof UserAppUsers*/
						|| relationObj.getContainerObj() instanceof ProjectAppAdminAreaChild
						|| relationObj.getContainerObj() instanceof ProjectAppAdminAreaProjects
						|| relationObj.getContainerObj() instanceof ProjectAppUsers
						|| relationObj.getContainerObj() instanceof StartAppAdminAreas
						|| relationObj.getContainerObj() instanceof StartAppUsers
						|| relationObj.getContainerObj() instanceof StartAppProjects
						|| relationObj.getContainerObj() instanceof ProjectAdminAreaProjectApplications
						|| relationObj.getContainerObj() instanceof UserProjectAdminAreaChild
						|| relationObj.getContainerObj() instanceof DirectoryApplications
						|| relationObj.getContainerObj() instanceof ProjectUserAdminAreaChild
						|| relationObj.getContainerObj() instanceof UserAdminAreaChild
						|| relationObj.getContainerObj() instanceof ProjectProjectApplications
						|| relationObj.getContainerObj() instanceof SiteAdminAreaUserApplications
						|| relationObj.getContainerObj() instanceof SiteAdminAreaProjectApplications
						|| relationObj.getContainerObj() instanceof AdminAreaProjectApplications
						|| relationObj.getContainerObj() instanceof AdminAreaUserApplications
						|| relationObj.getContainerObj() instanceof ProjectUserAAProjectApplications
						|| relationObj.getContainerObj() instanceof UserProjectAAProjectApplications
						|| relationObj.getContainerObj() instanceof UserAAUserApplications) {
					return;
				} else {
					createMenuItemForRelationObj(selections, manager);
				}
			} else if (selectionObj instanceof Role) {
				final Role role = (Role) selectionObj;
				if (!CommonConstants.SuperAdminRole.NAME.equals(role.getRoleName())) {
					createMenuItemForIAdminTreeChild(selections, manager);
				} 
			} else {
				createMenuItemForIAdminTreeChild(selections, manager);
			}
		}
	}

	/**
	 * Creates the menu item for I admin tree child.
	 *
	 * @param selections the selections
	 * @param manager the manager
	 */
	private void createMenuItemForIAdminTreeChild(final IStructuredSelection selections, final IMenuManager manager) {
		final Object selectionObj = selections.getFirstElement();
		if (!selections.toList().isEmpty()) {
			boolean isActiveState = false;
			boolean isDeActiveState = false;
			for (final Object object : selections.toList()) {
				if (object instanceof Site) {
					final Site siteModel = (Site) object;
					final boolean active = siteModel.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof AdministrationArea) {
					final AdministrationArea administrationArea = (AdministrationArea) object;
					final boolean active = administrationArea.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof Project) {
					final Project project = (Project) object;
					final boolean active = project.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof User) {
					final User user = (User) object;
					final boolean active = user.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
					manager.add(new Separator());
				} else if (object instanceof UserApplication) {
					final UserApplication userApplication = (UserApplication) object;
					final boolean active = userApplication.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof ProjectApplication) {
					final ProjectApplication projectApplication = (ProjectApplication) object;
					final boolean active = projectApplication.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof StartApplication) {
					final StartApplication startApplication = (StartApplication) object;
					final boolean active = startApplication.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof BaseApplication) {
					final BaseApplication baseApplication = (BaseApplication) object;
					final boolean active = baseApplication.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof ProjectCreateEvtAction) {
					final ProjectCreateEvtAction projectCreateEvtAction = (ProjectCreateEvtAction) object;
					final boolean active = projectCreateEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof ProjectDeleteEvtAction) {
					final ProjectDeleteEvtAction projectDeleteEvtAction = (ProjectDeleteEvtAction) object;
					final boolean active = projectDeleteEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof ProjectDeactivateEvtAction) {
					final ProjectDeactivateEvtAction projectDeactivateEvtAction = (ProjectDeactivateEvtAction) object;
					final boolean active = projectDeactivateEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof ProjectActivateEvtAction) {
					final ProjectActivateEvtAction projectActivateEvtAction = (ProjectActivateEvtAction) object;
					final boolean active = projectActivateEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof UserProjectRelAssignEvtAction) {
					final UserProjectRelAssignEvtAction userProjectRelAssignEvtAction = (UserProjectRelAssignEvtAction) object;
					final boolean active = userProjectRelAssignEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				} else if (object instanceof UserProjectRelRemoveEvtAction) {
					final UserProjectRelRemoveEvtAction userProjectRelRemoveEvtAction = (UserProjectRelRemoveEvtAction) object;
					final boolean active = userProjectRelRemoveEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				}  else if (object instanceof ProAARelRemoveEvtAction) {
					final ProAARelRemoveEvtAction proAARelRemoveEvtAction = (ProAARelRemoveEvtAction) object;
					final boolean active = proAARelRemoveEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				}  else if (object instanceof ProAARelAssignEvtAction) {
					final ProAARelAssignEvtAction proAARelAssignEvtAction = (ProAARelAssignEvtAction) object;
					final boolean active = proAARelAssignEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				}   else if (object instanceof UserProRelExpEvtAction) {
					final UserProRelExpEvtAction userProRelExpEvtAction = (UserProRelExpEvtAction) object;
					final boolean active = userProRelExpEvtAction.isActive();
					if (active) {
						isActiveState = true;
					} else {
						isDeActiveState = true;
					}
				}
			}

			if (isActiveState && isDeActiveState) {
				manager.add(deActivate);
				manager.add(activate);
			} else if (isActiveState) {
				manager.add(deActivate);
			} else if (isDeActiveState) {
				manager.add(activate);
			}
			manager.add(new Separator());
		}

		if (!(selections.toList().size() > 1)) {
			if (!(selectionObj instanceof User)) {
				manager.add(createAs);
			}
			manager.add(change);
			manager.add(delete);
			manager.add(new Separator());
			manager.add(gotoAction);
		}else{
			manager.add(delete);
		}

	}

	/**
	 * Creates the menu item for relation obj.
	 *
	 * @param selections
	 *            the selections
	 * @param manager
	 *            the manager
	 */
	private void createMenuItemForRelationObj(IStructuredSelection selections, IMenuManager manager) {
		boolean isActiveState = false;
		boolean isDeActiveState = false;
		for (final Object selectionObj : selections.toList()) {
			final RelationObj relationObj = (RelationObj) selectionObj;
			final IAdminTreeChild containerObj = relationObj.getContainerObj();
			final boolean active = relationObj.isActive();
			if (containerObj instanceof SiteAdministrationChild || containerObj instanceof SiteAdminAreaProjectChild
					|| containerObj instanceof AdminAreaProjectChild
					|| containerObj instanceof SiteAdminAreaUserAppNotFixed
					|| containerObj instanceof SiteAdminAreaUserAppFixed
					|| containerObj instanceof SiteAdminAreaUserAppProtected
					|| containerObj instanceof AdminAreaUserAppProtected
					|| containerObj instanceof AdminAreaUserAppNotFixed || containerObj instanceof AdminAreaUserAppFixed
					|| containerObj instanceof SiteAdminProjectAppNotFixed
					|| containerObj instanceof SiteAdminProjectAppFixed
					|| containerObj instanceof SiteAdminProjectAppProtected
					|| containerObj instanceof AdminAreaProjectAppNotFixed
					|| containerObj instanceof AdminAreaProjectAppFixed
					|| containerObj instanceof AdminAreaProjectAppProtected
					|| containerObj instanceof ProjectAdminAreaProjectAppNotFixed
					|| containerObj instanceof ProjectAdminAreaProjectAppFixed
					|| containerObj instanceof ProjectAdminAreaProjectAppProtected
					|| containerObj instanceof SiteAdminAreaStartApplications
					|| containerObj instanceof AdminAreaStartApplications
					|| containerObj instanceof ProjectAdminAreaStartApplications
					|| containerObj instanceof SiteAdminAreaProjectStartApplications
					|| containerObj instanceof AdminAreaProjectStartApplications
					|| containerObj instanceof ProjectUserChild || containerObj instanceof UserProjectChild
					|| containerObj instanceof UserStartApplications
					|| containerObj instanceof ProjectAdminAreaChild) {
				if (active) {
					isActiveState = true;
				} else {
					isDeActiveState = true;
				}
			}
		}
		if (isActiveState && isDeActiveState) {
			manager.add(deActivateRel);
			manager.add(activateRel);
		} else if (isActiveState) {
			manager.add(deActivateRel);
		} else if (isDeActiveState) {
			manager.add(activateRel);
		}
		manager.add(removeRelation);

		final Object selectionObj = selections.getFirstElement();
		final RelationObj relationObj = (RelationObj) selectionObj;
		final IAdminTreeChild containerObj = relationObj.getContainerObj();
		if (containerObj instanceof ProjectUserChild && selections.size() == 1) {
			manager.add(expiryDaysAction);
		}
	}

	/**
	 * Make actions.
	 */
	private void makeActions() {
		
		createAs = ContextInjectionFactory.make(CreateAsAction.class, eclipseContext);
		registry.register(this.createAs::setText, (message) -> message.rightSideCMlabelNodeCreateAs);
		registry.register(this.createAs::setToolTipText, (message) -> message.rightSideCMlabelNodeCreateAs);

		change = ContextInjectionFactory.make(ChangeAction.class, eclipseContext);
		registry.register(this.change::setText, (message) -> message.rightSideCMlabelNodeChange);
		registry.register(this.change::setToolTipText, (message) -> message.rightSideCMlabelNodeChange);
		
		delete = ContextInjectionFactory.make(DeleteAction.class, eclipseContext);
		registry.register(this.delete::setText, (message) -> message.rightSideCMlabelNodeDelete);
		registry.register(this.delete::setToolTipText, (message) -> message.rightSideCMlabelNodeDelete);

		gotoAction = ContextInjectionFactory.make(GoToAction.class, eclipseContext);
		registry.register(this.gotoAction::setText, (message) -> message.rightSideCMlabelNodeGoTo);
		registry.register(this.gotoAction::setToolTipText, (message) -> message.rightSideCMlabelNodeGoTo);

		activate = ContextInjectionFactory.make(ActivateAction.class, eclipseContext);
		registry.register(this.activate::setText, (message) -> message.popupmenulabelnodeActive);
		registry.register(this.activate::setToolTipText, (message) -> message.popupmenulabelnodeActive);

		deActivate = ContextInjectionFactory.make(DeActivateAction.class, eclipseContext);
		registry.register(this.deActivate::setText, (message) -> message.popupmenulabelnodeDeActive);
		registry.register(this.deActivate::setToolTipText, (message) -> message.popupmenulabelnodeDeActive);

		removeRelation = ContextInjectionFactory.make(RemoveRelationAction.class, eclipseContext);
		registry.register(this.removeRelation::setText, (message) -> message.popupmenulabelnodeRemoveRelation);
		registry.register(this.removeRelation::setToolTipText, (message) -> message.popupmenulabelnodeRemoveRelation);

		expiryDaysAction = ContextInjectionFactory.make(ExpiryDaysAction.class, eclipseContext);
		registry.register(this.expiryDaysAction::setText, (message) -> message.popupmenuExpiryDays);
		registry.register(this.expiryDaysAction::setToolTipText, (message) -> message.popupmenuExpiryDays);
		
		activateRel = ContextInjectionFactory.make(ActivateRelAction.class, eclipseContext);
		registry.register(this.activateRel::setText, (message) -> message.popupmenulabelnodeActive);
		registry.register(this.activateRel::setToolTipText, (message) -> message.popupmenulabelnodeActive);
		
		deActivateRel = ContextInjectionFactory.make(DeActivateRelAction.class, eclipseContext);
		registry.register(this.deActivateRel::setText, (message) -> message.popupmenulabelnodeDeActive);
		registry.register(this.deActivateRel::setToolTipText, (message) -> message.popupmenulabelnodeDeActive);
	}

}
