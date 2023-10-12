clear; close all;

% create a matrix with edges: initial node, final node, and weight
edges =  [...
    1 2 2; 
    1 3 5; 
    1 4 4; 
    2 3 2; 
    2 5 7; 
    3 4 1; 
    3 5 4; 
    3 6 3;
    4 6 3;
    5 6 1; 
    5 7 5; 
    6 7 7];

% give names to the nodes and create the graph object
names = {'O' 'A' 'B' 'C' 'D' 'E' 'T'};
G = graph(edges(:,1), edges(:,2), edges(:,3), names);


figure;

% plot the graph and highlight its minimal spanning tree
subplot(2,1,1)
p = plot(G,'EdgeLabel',G.Edges.Weight);
[T,pred] = minspantree(G);
view([-90 90]);
highlight(p,T)
title('Minimal Spanning Tree')

% plot the graph and highlith its shortest path tree starting at O
subplot(2,1,2)
TR = shortestpathtree(G,1);
p = plot(G,'EdgeLabel',G.Edges.Weight);
view([-90 90]);
highlight(p,TR)
title('Shortest Path Tree')