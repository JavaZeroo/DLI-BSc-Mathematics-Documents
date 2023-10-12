clear; close all;

% create a matrix with edges: initial node, final node, and weight
edges =  [...
    1 2 5; 
    1 3 7; 
    1 4 4; 
    2 3 1; 
    2 5 3; 
    3 4 2; 
    3 5 4; 
    3 6 5;
    4 6 4;
    5 7 9; 
    6 5 1; 
    6 7 6];

% give names to the nodes and create the graph object
names = {'O' 'A' 'B' 'C' 'D' 'E' 'T'};
G = digraph(edges(:,1), edges(:,2), edges(:,3), names);

figure;

% plot the original network
subplot(2,1,1)
p = plot(G,'EdgeLabel',G.Edges.Weight);
title('Original network')
view([-90 90]);

%compute and plot the maximal flow and the minimal cut
subplot(2,1,2)
[mf,GF,cs,ct] = maxflow(G,1,7);
H = plot(G,'EdgeLabel',G.Edges.Weight);
view([-90 90]);
H.EdgeLabel = {};
highlight(H,GF,'EdgeColor','r','LineWidth',2);
st = GF.Edges.EndNodes;
labeledge(H,st(:,1),st(:,2),GF.Edges.Weight);
title(['Max Flow from O to T (value |f| = ' num2str(mf) ') and minimal cut'])
highlight(H,cs,'NodeColor','blue', 'MarkerSize', 10)
highlight(H,ct,'NodeColor','black', 'MarkerSize', 10)

