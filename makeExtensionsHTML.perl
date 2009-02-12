#!/usr/bin/perl
# Copyright 2009 Egon Willighagen <egonw@users.sf.net>
#
# License: Eclipse Public License
#
# Needs the command line utilities xpath and xsltproc. (Ubuntu: xsltproc, libxml-xpath-perl)
#
use diagnostics;
use strict;

my %extensionPoints = (
  'content-type', 'org.eclipse.core.runtime.contentTypes'
);

open(my $INDEX, '>', 'ep.index.html') or die $!;
print $INDEX "<html>\n";
print $INDEX "<head>\n";
print $INDEX "  <title>Extension Points used in Bioclipse</title>\n";
print $INDEX "</head>\n";
print $INDEX "<body>\n";
print $INDEX "  <h1>Extension Points used in Bioclipse</h1>\n";
print $INDEX "<ul>\n";
while ( my ($ep, $id) = each(%extensionPoints) ) {
  print $INDEX "<li>\n";
  print $INDEX "<a href=\"ep.$ep.html\">$ep</a>\n";
  system('echo "<list>" > ep.'.$ep.'.xml');
  system('xpath -q -e "//extension[@point=\''.$id.'\']" `find . -name plugin.xml` >> ep.'.$ep.'.xml');
  system('echo "</list>" >> ep.'.$ep.'.xml');
  system('xsltproc ep2html.xslt ep.'.$ep.'.xml > ep.'.$ep.'.html');
  print $INDEX "</li>\n";
}
print $INDEX "</ul>\n";
print $INDEX "</body>\n";
print $INDEX "</html>\n";

