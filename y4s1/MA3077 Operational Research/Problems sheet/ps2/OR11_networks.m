clear; close all;

% O A B C D E T
% 1 2 3 4 5 6 7 
% create a matrix with edges: initial node, final node, and weight
edges =  [...
    1 2 4;
    1 3 3;
    1 4 5;
    2 3 1;
    2 5 2;
    3 5 4;
    3 6 8;
    3 4 2;
    4 6 3;
    5 6 2;
    5 7 4;
    6 7 8;
    ];

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