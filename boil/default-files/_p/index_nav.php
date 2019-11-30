<nav class="animenu rtable"> 
  <button   id="mnuBtn" class="animenu__toggle" onclick="javascript:JSSHOP.ui.toggleVisibility('mnuT');">
    <span class="animenu__toggle__bar"></span>
    <span class="animenu__toggle__bar"></span>
    <span class="animenu__toggle__bar"></span>
  </button>
  <ul class="nanimenu animenu__nav" id="mnuT" style="display:none;float: right">
  
      <li>
      <a href="index.php" onclick="javascript:JSSHOP.ui.setCBBClickClr(this,'brdrClrHdr','brdrClrWhite', function(){v='s';});" class="transition">Home</a>
      </li>
 
    
    
    <li>
      <a href="javascript:void(0);" onclick="javascript:JSSHOP.ui.toggleVisibility('dvmm01');">Empresa</a>
      <ul class="animenu__nav__child"  id="dvmm01">
        <li><a href="./">Sobre</a></li>
        <li><a  href="javascript:loadJSModal('tplates/modal_prodlinks.html');">Contatos</a></li>
      </ul>
    </li>     
    <li>
      <a href="javascript:void(0);" onclick="javascript:JSSHOP.ui.toggleVisibility('dvmm02');">settings</a>
      <ul class="animenu__nav__child"  id="dvmm02">
        <li><a href="<?=SHP_WEBDIR; ?>/adminshop/?modID=CViewEditMainSettings">Main Settings</a></li>
        <li><a href="<?=SHP_WEBDIR; ?>/adminshop/?modID=CViewEditAdminSettings">Admin Settings</a></li>
        <li><a href="<?=SHP_WEBDIR; ?>/adminshop/?modID=CViewEditCheckoutSettings">Checkout Settings</a></li>
        <li><a href="<?=SHP_WEBDIR; ?>/adminshop/?modID=CViewEditCurrencySettings">Currency  Settings</a></li>

      </ul>
    </li> 


    <li>
      <a href="javascript:void(0);" onclick="javascript:JSSHOP.ui.toggleVisibility('dvmm03');">Produtos </a>
      <ul class="animenu__nav__child"  id="dvmm03">
<li class="ihnavli"> <a href="produtos.php">Nossos Produtos</a> </li>
	 
	<li class="ihnavli ihnavlibrdr"><div onclick="javascript:JSSHOP.ui.setCBBClickClr(this,'brdrClrHdr','brdrClrWhite', function(){xx='yy'});"> <a href="produtos_estores-exteriores.php">
	estores exteriores</a></div> </li>
	<li class="ihnavli ihnavlibrdr"> <a href="produtos_janelas-portadas.php">
	janelas e portadas</a> </li>
<li class="ihnavli ihnavlibrdr">
<a href="produtos_portas-portoes.php">
	portas e portões</a></li>
<li class="ihnavli ihnavlibrdr">
<a href="produtos_grades-gradeamentos.php">
	grades de protecção</a>
	</li>
<li class="ihnavli ihnavlibrdr">
	<a href="produtos_marquises.php">
	marquises</a></li>
<li class="ihnavli ihnavlibrdr">
	<a  href="produtos_coberturas.php">
	coberturas</a></li>
<li class="ihnavli ihnavlibrdr">
	<a href="produtos_divisorias-escritorios.php">
	divisórias para escritórios</a></li>
<li class="ihnavli ihnavlibrdr">
	<a href="produtos_resguardos-banheira.php">
	resguardos para banheiras</a></li>
<li class="ihnavli ihnavlibrdr">
	<a href="produtos_vedacoes-jardins.php">
	vedações para jardins</a></li>

      </ul>
    </li> 


    <li>
      <a href="javascript:void(0);" onclick="javascript:JSSHOP.ui.toggleVisibility('dvmm04');">settings</a>
      <ul class="animenu__nav__child"  id="dvmm04">
        <li><a href="">Sub Item 1</a></li>
        <li><a href="">Sub Item 2</a></li>
      </ul>
    </li> 


    <li>
      <a href="javascript:void(0);" onclick="javascript:JSSHOP.ui.toggleVisibility('dvmm05');">settings </a>
      <ul class="animenu__nav__child"  id="dvmm05">
        <li><a href="">Sub Item 1</a></li>
        <li><a href="">Sub Item 2</a></li>
      </ul>
    </li> 
  </ul>
</nav>


<script type="javascript" language="text/javascript">
(function(){
  var animenuToggle = document.querySelector('.animenu__toggle'),
      animenuNav    = document.querySelector('.animenu__nav'),
      hasClass = function( elem, className ) {
        return new RegExp( ' ' + className + ' ' ).test( ' ' + elem.className + ' ' );
      },
      toggleClass = function( elem, className ) {
        var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ' ) + ' ';
        if( hasClass(elem, className ) ) {
          while( newClass.indexOf( ' ' + className + ' ' ) >= 0 ) {
            newClass = newClass.replace( ' ' + className + ' ' , ' ' );
          }
          elem.className = newClass.replace( /^\s+|\s+$/g, '' );
        } else {
          elem.className += ' ' + className;
        }
      },           
      animenuToggleNav =  function (){        
        toggleClass(animenuToggle, "animenu__toggle--active");
        toggleClass(animenuNav, "animenu__nav--open");        
      }

  if (!animenuToggle.addEventListener) {
      animenuToggle.attachEvent("onclick", animenuToggleNav);
  }
  else {
      animenuToggle.addEventListener('click', animenuToggleNav);
  }
})()
</script>
