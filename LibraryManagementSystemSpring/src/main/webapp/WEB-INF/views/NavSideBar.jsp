<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.jsp">GCIT Library Management System</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Stuart Zhang <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li>
                <a href="index"><i class="fa fa-fw fa-dashboard"></i> Home</a>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#librarian"><i class="fa fa-fw fa-arrows-v"></i> Librarian <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="librarian" class="collapse">
                    <li>
                        <a data-toggle = "modal" data-target = "#branchSelection" href="librarian_login_modal">View/Update Branch Info</a>
                    </li>
                </ul>
            </li>
             <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#borrower"><i class="fa fa-fw fa-arrows-v"></i> Borrower <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="borrower" class="collapse">
                    <li>
                        <a data-toggle = "modal" data-target = "#branchSelection" href = "borrower_checkOut_modal">Check-Out Books</a>
                    </li>
                    <li>
                        <a data-toggle = "modal" data-target = "#branchSelection" href = "borrower_checkIn_modal">Check-In Books</a>
                    </li>
                </ul>
            </li>
             <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#admin"><i class="fa fa-fw fa-arrows-v"></i> Administrator <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="admin" class="collapse">
                    <li>
                        <a href="admin_book">Manage Books</a>
                    </li>
                    <li>
                        <a href="admin_author">Manage Authors</a>
                    </li>
                    <li>
                        <a href="admin_genre">Manage Genres</a>
                    </li>
                    <li>
                        <a href="admin_publisher">Manage Publishers</a>
                    </li>
                    <li>
                        <a href="admin_library_branch">Manage Library Branches</a>
                    </li>
                    <li>
                        <a href="admin_borrower">Manage Borrowers</a>
                    </li>
                    <li>
                        <a href="admin_book_loans">Manage Book Loans</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Link</a>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
 <!-- MODAL WRAPPER FOR BRANCH SELECTION-->
<div class="modal fade" id="branchSelection" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    </div>
	</div>
</div>
